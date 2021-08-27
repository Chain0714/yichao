import request from '@/utils/request'

// 查询监测点信息列表
export function listPoint(query) {
  return request({
    url: '/pullution/point/list',
    method: 'get',
    params: query
  })
}

// 查询监测点信息详细
export function getPoint(id) {
  return request({
    url: '/pullution/point/' + id,
    method: 'get'
  })
}

// 新增监测点信息
export function addPoint(data) {
  return request({
    url: '/pullution/point',
    method: 'post',
    data: data
  })
}

// 修改监测点信息
export function updatePoint(data) {
  return request({
    url: '/pullution/point',
    method: 'put',
    data: data
  })
}

// 删除监测点信息
export function delPoint(id) {
  return request({
    url: '/pullution/point/' + id,
    method: 'delete'
  })
}

// 导出监测点信息
export function exportPoint(query) {
  return request({
    url: '/pullution/point/export',
    method: 'get',
    params: query
  })
}
