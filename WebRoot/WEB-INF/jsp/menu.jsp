<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div class="span24">
	<ul class="mainNav">
		<li>
			<a href="${pageContext.request.contextPath}/index.action">首页</a>
			|
		</li>
		<s:iterator var="cate" value="#session.cateList">
			<li>
				<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#cate.cid"/>&page=1"><s:property value="#cate.cname"/></a>
				|
			</li>
		</s:iterator>
	</ul>
</div>