package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.DAO_NhanVien;
import dto.NhanVien;

@RestController
public class API_NhanVien {

	@PostMapping(value = "/nhanvien_search")
	@ResponseBody
	public NhanVien[] search(@RequestBody NhanVien nhanvien) {
		ArrayList<NhanVien> arr = DAO_NhanVien.search(nhanvien);
		return arr.toArray(new NhanVien[arr.size()]);
	}
	
	@GetMapping(value = "/nhanvien_getall")
	public NhanVien[] getAll() {
		ArrayList<NhanVien> arr = DAO_NhanVien.search(new NhanVien(-1, null, null));
		return arr.toArray(new NhanVien[arr.size()]);
	}
	
	@PostMapping(value = "/nhanvien_find")
	@ResponseBody
	public NhanVien find(@RequestBody String[] code) {
		return DAO_NhanVien.findById(Integer.parseInt(code[0]));
	}
	
	@PostMapping(value = "/nhanvien_create")
	@ResponseBody
	public int create(@RequestBody NhanVien nhanvien) {
		return DAO_NhanVien.create(nhanvien);
	}
	
	@PostMapping(value = "/nhanvien_update")
	@ResponseBody
	public int update(@RequestBody NhanVien[] nhanvien) {
		return DAO_NhanVien.update(nhanvien[0], nhanvien[1]);
	}
	
	@PostMapping(value = "/nhanvien_delete")
	@ResponseBody
	public int delete(@RequestBody NhanVien nhanvien) {
		return DAO_NhanVien.delete(nhanvien);
	}
}