package com.yhh.shop.order;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yhh.shop.cart.Cart;
import com.yhh.shop.cart.CartItem;
import com.yhh.shop.user.User;
import com.yhh.shop.utils.PageUtils;
import com.yhh.shop.utils.PaymentUtil;

/**
 * 订单的Action
 * @author 浩瀚
 *
 */
@SuppressWarnings("serial")
public class OrderAction extends ActionSupport{
	
	private Order order;
	
	private String pd_FrpId;
	
	// 付款成功后的需要的参数:
	private String r3_Amt;
	
	private String r6_Order;
	
	//查询订单所用的ID
	private Integer oid;
	
	// 当前页数
	private Integer page;
	
	// 订单状态
	private Integer state;
	
	// 用于分页显示的集合
	private PageUtils<Order> pageBean;
	
	//注入Service
	@Resource(name="orderService")
	private OrderService orderService;
	
	public String getPd_FrpId() {
		return pd_FrpId;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	public String getR3_Amt() {
		return r3_Amt;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public String getR6_Order() {
		return r6_Order;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public PageUtils<Order> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageUtils<Order> pageBean) {
		this.pageBean = pageBean;
	}
	
	public String saveOrder(){
		
		order = new Order();
		/*******封装订单数据*******/
		order.setOrdertime(new Date());
		order.setState(0); // 0 未付款  1 已付款 2 未发货 3 已收货
		//获得购物车对象
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//从Cart中获取数据
		if(cart == null){
			this.addActionMessage("购物车为空，请先去购物");
			return "saveOrderMessage";
		}
		order.setTotal(cart.getTotal());
		//订单所属的用户
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user == null){
			this.addActionMessage("您还没有登录，请先登录");
			return "loginuserMessage";
		}
		order.setUser(user);
		/*******封装订单项数据*******/
		
		for (CartItem cartItem : cart.getCartItems()) {
			// 将购物项的数据封装到订单项中
			
			OrderItem orderItem = new OrderItem();
		
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubTotal());
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			
			order.getOrderList().add(orderItem);
		}
		cart.clear();
		//保存订单
		Integer oid = orderService.saveOrder(order);
		
		order = orderService.findByOid(oid); 
		System.out.println(order);
		return "saveOrderSuccess";
	}

	/**
	 * 为订单付款的方法
	 * @throws IOException 
	 * 
	 */
	public String payOrder() throws IOException{
		//修改订单：
		//查询当前ID的订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//付款：
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://192.168.1.109:8080/ssh_shop/order_callBack.action";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,pd_FrpId , pr_NeedResponse, keyValue);
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		System.out.println(sb.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.sendRedirect(sb.toString());
		return NONE;
	}

	/**
	 * 付款成功后的回调方法
	 */
	public String callBack(){
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);// 修改订单状态.
		orderService.update(currOrder);
		
		this.addActionMessage("订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
		return "callBackSuccess";
	}
	
	/**
	 * 按用户ID查询订单
	 */
	public String findByUid(){
		//获得用户对象
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		List<Order> orderList = orderService.findByUid(existUser);
		// 将集合压栈
		ActionContext.getContext().getValueStack().set("orderList", orderList);
		return "findByUidSuccess";
	}
	
	/**
	 * 按订单ID查询单个订单
	 * @return
	 */
	public String findByOid(){
		
		order = orderService.findByOid(oid);
		
		return "findByOidSuccess";
	}
	
	/**
	 * 后台:按状态查询订单
	 */
	public String adminFindByState(){
		PageUtils<Order> pageBean = orderService.findByPage(page,state);
		return "adminFindByStateSuccess";
	}
	
	/**
	 * 查询所有订单
	 */
	public String adminFindAll(){
		pageBean = orderService.findByPage(page); 
		return "adminFindAllSuccess";
	}

	/**
	 * 后台修改订单状态
	 */
	public String adminUpdateState(){
		// 根据ID查询订单:
		order = orderService.findByOid(oid);
		order.setState(3);
		orderService.update(order);
		
		return "adminUpdateStateSuccess";
	}
	
	/**
	 * 前台修改订单状态
	 */
	public String updateState(){
		// 根据ID查询订单:
		order = orderService.findByOid(oid);
		order.setState(4);
		orderService.update(order);
		
		return "updateStateSuccess";
	}

	
}