import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

/**
 * 去除token中的Bearer前缀
 * @param {string} token - 原始token
 * @returns {string} - 去除前缀后的token
 */
function removeBearerPrefix(token) {
  if (!token) {
    return token
  }
  // 使用正则表达式去除Bearer前缀（不区分大小写，支持多个空格）
  return token.trim().replace(/^Bearer\s+/i, '').trim()
}

export function getToken() {
  const token = Cookies.get(TokenKey)
  // 返回时确保去除Bearer前缀，保证返回纯token
  return removeBearerPrefix(token)
}

export function setToken(token) {
  // 存储时去除Bearer前缀，确保存储的是纯token
  const cleanToken = removeBearerPrefix(token)
  return Cookies.set(TokenKey, cleanToken)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
