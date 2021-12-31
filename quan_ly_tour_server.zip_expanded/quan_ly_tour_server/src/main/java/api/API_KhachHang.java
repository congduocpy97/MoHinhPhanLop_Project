package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_KhachHang;
import dto.KhachHang;

@RestController
public class API_KhachHang {

	@PostMapping(value = "/khachhang_search")
	@ResponseBody
	public KhachHang[] search(@RequestBody KhachHang khachhang) {
		ArrayList<KhachHang> arr = DAO_KhachHang.search(khachhang);
		return arr.toArray(new KhachHang[arr.size()]);
	}
	
	@GetMapping(value = "/khachhang_getall")
	public KhachHang[] getAll() {
		ArrayList<KhachHang> arr = DAO_KhachHang.search(new KhachHang(-1, null, null, null, null, null, null, null));
		return arr.toArray(new KhachHang[arr.size()]);
	}
	
	@PostMapping(value = "/khachhang_find")
	@ResponseBody
	public KhachHang find(@RequestBody String[] code) {
		return DAO_KhachHang.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/khachhang_create")
	@ResponseBody
	public int create(@RequestBody KhachHang khachhang) {
		System.out.println(khachhang.getSDT());
		return DAO_KhachHang.create(khachhang);
	}
	
	@PostMapping(value = "/khachhang_update")
	@ResponseBody
	public int update(@RequestBody KhachHang[] khachhang) {
		return DAO_KhachHang.update(khachhang[0], khachhang[1]);
	}
	
	@PostMapping(value = "/khachhang_delete")
	@ResponseBody
	public int delete(@RequestBody KhachHang khachhang) {
		return DAO_KhachHang.delete(khachhang);
	}
}