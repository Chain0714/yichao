import request from '@/utils/request'

// 查询历史查询列表
export function listHistory(query) {
  return request({
    url: '/pullution/history/list',
    method: 'get',
    params: query
  })
}// 查询历史查询列表
export function listReal(query) {
  return request({
    url: '/pullution/history/listReal',
    method: 'get',
    params: query
  })
}

// 查询历史查询详细
export function getHistory(id) {
  return request({
    url: '/pullution/history/' + id,
    method: 'get'
  })
}

// 新增历史查询
export function addHistory(data) {
  return request({
    url: '/pullution/history',
    method: 'post',
    data: data
  })
}

// 修改历史查询
export function updateHistory(data) {
  return request({
    url: '/pullution/history',
    method: 'put',
    data: data
  })
}

// 删除历史查询
export function delHistory(id) {
  return request({
    url: '/pullution/history/' + id,
    method: 'delete'
  })
}

// 导出历史查询
export function exportHistory(query) {
  return request({
    url: '/pullution/history/export',
    method: 'get',
    params: query
  })
}
