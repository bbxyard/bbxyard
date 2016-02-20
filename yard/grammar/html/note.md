# CSS样式
## 作用
## 怎么写
	选择器{
		声明1；   <-->  属性：值；
		声明2；
		。。。。。。
	}
	选择器分类
		标签选择器（如	h1{......}  ）
		类选择器（如  .red{........} ）  class="red"
		ID选择器(如  #green{........})   id="green"


## 怎么用
	（1）内部样式表
	<style>
		css样式
	</style>
	（2）内联样式
	<p style="css样式"></p>
	（3）外部样式表
	.css文件
	在网页中导入或者引入外部样式文件即可
	<link>

	@import
优先级问题：
ID选择器>类选择器>标签选择器
内联样式>内部样式>外部样式

    <style>
    	p{

    	}
    	.red{

    	}
    </style>

<p class="red">


## 如何编写自己样式声明？
### 学习方法
 + 查看W3CSchool手册，定位到CSS部分
 + 编写CSS样式时，可以借助工具的提示来调错
### 常见问题及错误
 - CSS文件中设置的中文字体类型不起作用，怎么解决?
    css文件编码重新设置为utf-8即可
 - 如果页面中既有中文又有英文，怎么修饰字体类型？
    font-family:cursive,"微软雅黑";
    请把英文字体类型写在中文字体类型之前


h1{
	font-size:23px;
	color:red;
}

字体
font 简写,可以按顺序设置如下属性：
	font-style
	font-weight
	font-size/line-height
	font-family
补充讲解：****
span和p不一样
（1）span不会独占一行，p会独占一行
行内元素span，很常用，记住
块级元素p ，很常用的块级元素<div>
（2）块级元素可设置宽高，行级（行内）元素设置宽高不起作用

文本
	color
	text-align
	text-indent:(em)
	line-height
	text-decoration
## 如何设置页面中超链接的样式？
     超链接未选中
     鼠标定位上去之后
     鼠标点下去之后
     访问过以后

## 超链接
## 背景
## 列表
