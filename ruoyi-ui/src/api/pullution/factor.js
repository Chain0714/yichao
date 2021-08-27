import request from '@/utils/request'

// 查询因子信息列表
export function listFactor(query) {
  return request({
    url: '/pullution/factor/list',
    method: 'get',
    params: query
  })
}

// 查询因子信息详细
export function getFactor(id) {
  return request({
    url: '/pullution/factor/' + id,
    method: 'get'
  })
}

// 新增因子信息
export function addFactor(data) {
  return request({
    url: '/pullution/factor',
    method: 'post',
    data: data
  })
}

// 修改因子信息
export function updateFactor(data) {
  return request({
    url: '/pullution/factor',
    method: 'put',
    data: data
  })
}

// 删除因子信息
export function delFactor(id) {
  return request({
    url: '/pullution/factor/' + id,
    method: 'delete'
  })
}

// 导出因子信息
export function exportFactor(query) {
  return request({
    url: '/pullution/factor/export',
    method: 'get',
    params: query
  })
}
