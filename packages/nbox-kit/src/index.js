/**
 * @file nbox-kit-index.js
 * @description 
 */

const KIT = Object.assign(
  {},
  KIT_COM_MISC,
  KIT_FS_FS,
  KIT_RPC_HTTP,
  KIT_RPC_WS
);

KIT.help = () => { console.log('kit: ', module.exports); }

module.exports = KIT;
