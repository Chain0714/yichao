<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="数据类型" prop="cn">
        <el-select v-model="queryParams.cn" placeholder="请选择数据类型" clearable size="small">
          <el-option
            v-for="dict in cnOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="监测点" prop="mn">
        <el-select v-model="queryParams.mn" placeholder="请选择监测点">
          <el-option
            v-for="point in pointOptions"
            :key="point['id']"
            :label="point['name']"
            :value="point['mn']"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="监测因子">
        <el-select v-model="factorCode" placeholder="请选择监测因子">
          <el-option
            v-for="factor in factorOptions"
            :key="factor['id']"
            :label="factor['name']"
            :value="factor['code']"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="数据时间">
        <el-date-picker
          v-model="daterangeDataTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['pullution:history:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="historyList" @selection-change="handleSelectionChange">
      <el-table-column label="数据类型" align="center" prop="cn" :formatter="cnFormat" />
      <el-table-column label="采集点" align="center" prop="pointName" />
      <el-table-column label="因子名称" align="center" prop="factorName" />
      <el-table-column label="因子值" align="center" prop="factorVal" />
      <el-table-column label="数据时间" align="center" prop="dataTime" width="180" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listHistory, getHistory, delHistory, addHistory, updateHistory, exportHistory } from "@/api/pullution/history";
import { listFactor } from "@/api/pullution/factor";
import { listPoint } from "@/api/pullution/point";

export default {
  name: "History",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 历史查询表格数据
      historyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 数据类型字典
      cnOptions: [],
      pointOptions: [],
      factorOptions: [],
      // 数据时间时间范围
      daterangeDataTime: [],
      factorCode: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cn: null,
        mn: null,
        dataTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mn: [
          { required: true, message: "设备编码不能为空", trigger: "blur" }
        ],
        dataTime: [
          { required: true, message: "数据时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("pullution_data_type").then(response => {
      this.cnOptions = response.data;
    });
    listPoint({}).then(response => {
        this.pointOptions = response.rows;
    });
    listFactor({}).then(response => {
        this.factorOptions = response.rows;
    });
  },
  methods: {
    /** 查询历史查询列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeDataTime && '' != this.daterangeDataTime) {
        this.queryParams.params["beginDataTime"] = this.daterangeDataTime[0];
        this.queryParams.params["endDataTime"] = this.daterangeDataTime[1];
      }
      if(null != this.factorCode && '' !== this.factorCode){
          this.queryParams.params["factorCode"] = this.factorCode
      }
      listHistory(this.queryParams).then(response => {
        this.historyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 数据类型字典翻译
    cnFormat(row, column) {
      return this.selectDictLabel(this.cnOptions, row.cn);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        st: null,
        cn: null,
        mn: null,
        flag: null,
        dataTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeDataTime = [];
      this.factorCode = null;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加历史查询";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getHistory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改历史查询";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHistory(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHistory(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除历史查询编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delHistory(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有历史查询数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportHistory(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
