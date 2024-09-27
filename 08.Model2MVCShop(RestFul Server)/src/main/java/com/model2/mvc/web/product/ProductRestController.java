package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> ��ǰ��ϰ���  RestController
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	//��:getProduct
	@RequestMapping( value="json/getProduct/{prodNo}", method=RequestMethod.GET )
	public Product getProduct( @PathVariable int prodNo ) throws Exception{
		
		System.out.println("/user/json/getProduct : GET");
		
		//Business Logic
		return productService.getProduct(prodNo);
	}

	@RequestMapping( value="json/updateProduct", method=RequestMethod.POST )
	public Product updateProduct(	@RequestBody Product product,
									HttpSession session ) throws Exception{
	
		System.out.println("/product/json/updateProduct : POST");
		//Business Logic
		System.out.println("::"+product);
		Product dbProduct=productService.getProduct(product.getProdNo());
		
		if( product.getProdName().equals(dbProduct.getProdName())){
			session.setAttribute("product", dbProduct);
		}
		
		return dbProduct;
	}
}