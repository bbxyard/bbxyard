const nodemailer = require('nodemailer'); //发送邮件的node插件
const chalk = require('chalk');

const PASSWD = process.env.PASSWD;
if (!PASSWD) {
  console.error(chalk.red('请先配置smtp授权码, ') + chalk.blue('到邮箱设置下获取'));
  process.exit(1);
}

let transporter = nodemailer.createTransport({
    service: '126', // 发送者的邮箱厂商，支持列表：https://nodemailer.com/smtp/well-known/
    port: 465, // SMTP 端口
    secureConnection: true, // SSL安全链接
    auth: {   //发送者的账户密码
      user: 'pufan66@126.com', //账户
      pass: PASSWD, //smtp授权码，到邮箱设置下获取
    }
  });

const htmlContent = '<h1>Just do it.</h1>';

let mailOptions = {
    from: '"Brian" <pufan66@126.com>', // 发送者昵称和地址
    to: '77219522@qq.com', // 接收者的邮箱地址
    subject: '一封暖暖的小邮件', // 邮件主题
    html: htmlContent
    // text: 'test mail',  //邮件的text
    // html: html  //也可以用html发送  
  };
  
//发送邮件
transporter.sendMail(mailOptions, (error, info) => {  
  if (error) {
  return console.log(error);
  }
  console.log(chalk.green('邮件发送成功 ID：', info.messageId));
});
