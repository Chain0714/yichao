import request from '@/utils/request'

// 查询因子信息列表
export function listdevice(query) {
  return request({
    url: '/pullution/device/list',
    method: 'get',
    params: query
  })
}

// 查询因子信息详细
export function getdevice(id) {
  return request({
    url: '/pullution/device/' + id,
    method: 'get'
  })
}

// 新增因子信息
export function adddevice(data) {
  return request({
    url: '/pullution/device',
    method: 'post',
    data: data
  })
}

// 修改因子信息
export function testdevice(data) {
  return request({
    url: '/pullution/device',
    method: 'put',
    data: data
  })
}

// 删除因子信息
export function deldevice(id) {
  return request({
    url: '/pullution/device/' + id,
    method: 'delete'
  })
}

// 导出因子信息
export function exportdevice(query) {
  return request({
    url: '/pullution/device/export',
    method: 'get',
    params: query
  })
}
