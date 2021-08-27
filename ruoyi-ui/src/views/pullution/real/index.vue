<template>
  <div class="app-container home">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card v-for="(value, key) in realData" :key="key" class="update-log">
          <div slot="header" class="clearfix">
            <strong><span>{{key.split('|')[0]}}</span></strong>
          </div>
          <div class="body">
            <p v-for="val in value" :key="val.factorCode">
              <i class="el-icon-chat-dot-round"></i> {{val.factorName}}：<a href="javascript:;">{{val.factorVal}}</a>
            </p>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    import { listReal } from "@/api/pullution/history";
    import { listPoint } from "@/api/pullution/point";
    export default {
        name: "real",
        data() {
            return {
                realData: {},
                pointOptions: [],
                queryParams: {
                    mn: null,
                }
            };
        },
        created() {
            this.getList();
            listPoint({}).then(response => {
                this.pointOptions = response.rows;
            });
        },
        methods: {
            /** 查询历史查询列表 */
            getList() {
                this.queryParams.params = {};
                listReal(this.queryParams).then(response => {
                    this.realData = response;
                    console.log(response)
                });
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.getList();
            },
            /** 重置按钮操作 */
            resetQuery() {
                this.queryParams.mn=null;
                this.handleQuery();
            }
        }
    };
</script>

<style scoped lang="scss">
  .home {
    blockquote {
      padding: 10px 20px;
      margin: 0 0 20px;
      font-size: 17.5px;
      border-left: 5px solid #eee;
    }
    hr {
      margin-top: 20px;
      margin-bottom: 20px;
      border: 0;
      border-top: 1px solid #eee;
    }
    .col-item {
      margin-bottom: 20px;
    }

    ul {
      padding: 0;
      margin: 0;
    }

    font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 13px;
    color: #676a6c;
    overflow-x: hidden;

    ul {
      list-style-type: none;
    }

    h4 {
      margin-top: 0px;
    }

    h2 {
      margin-top: 10px;
      font-size: 26px;
      font-weight: 100;
    }

    p {
      margin-top: 10px;

      b {
        font-weight: 700;
      }
    }

    .update-log {
      ol {
        display: block;
        list-style-type: decimal;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0;
        margin-inline-end: 0;
        padding-inline-start: 40px;
      }
    }
  }
</style>
