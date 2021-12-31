package api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import dao.DAO_TrangThai;
import dto.TrangThai;

public class Suport {
	public static String createAPI(String table) {
		String result = ""
				+ "package api;\r\n"
				+ "\r\n"
				+ "import org.springframework.web.bind.annotation.PostMapping;\r\n"
				+ "import org.springframework.web.bind.annotation.RequestBody;\r\n"
				+ "import org.springframework.web.bind.annotation.ResponseBody;\r\n"
				+ "import org.springframework.web.bind.annotation.RestController;\r\n"
				+ "\r\n"
				+ "import dao.DAO_" + table + ";\r\n"
				+ "import dto." + table + ";\r\n"
				+ "\r\n"
				+ "@RestController\r\n"
				+ "public class API_" + table + " {\r\n"
				+ "\r\n"
				+ "	@PostMapping(value = \"/" + table.toLowerCase() + "_search\")\r\n"
				+ "	@ResponseBody\r\n"
				+ "	public " + table + "[] search(@RequestBody " + table + " " + table.toLowerCase() + ") {\r\n"
				+ "		return (" + table + "[]) DAO_" + table + ".search(" + table.toLowerCase() + ").toArray();\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	@PostMapping(value = \"/" + table.toLowerCase() + "_find\")\r\n"
				+ "	@ResponseBody\r\n"
				+ "	public " + table + " find(@RequestBody String code) {\r\n"
				+ "		return DAO_" + table + ".findById(Integer.parseInt(code));\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	@PostMapping(value = \"/" + table.toLowerCase() + "_create\")\r\n"
				+ "	@ResponseBody\r\n"
				+ "	public int create(@RequestBody " + table + " " + table.toLowerCase() + ") {\r\n"
				+ "		return DAO_" + table + ".create(" + table.toLowerCase() + ");\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	@PostMapping(value = \"/" + table.toLowerCase() + "_update\")\r\n"
				+ "	@ResponseBody\r\n"
				+ "	public int update(@RequestBody " + table + " " + table.toLowerCase() + ") {\r\n"
				+ "		return DAO_" + table + ".update(" + table.toLowerCase() + "[0], " + table.toLowerCase() + "[1]);\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	@PostMapping(value = \"/" + table.toLowerCase() + "_delete\")\r\n"
				+ "	@ResponseBody\r\n"
				+ "	public int delete(@RequestBody " + table + " " + table.toLowerCase() + ") {\r\n"
				+ "		return DAO_" + table + ".delete(" + table.toLowerCase() + ");\r\n"
				+ "	}\r\n"
				+ "}";
		return result;
	}
	
	public static void createModel(String name, String[] col, String[] key, String[] value) {
		String model = "const " + name + " = {\r\n";
		for(int i = 0; i < key.length; i++) {
			model += key[i] + ": " + (value[i].contentEquals("1") ? "-1,\r\n" : "null,\r\n");
		}
		model += "}\r\n";
		String field = "const Field" + " = {\r\n";
		for(int i = 0; i < key.length; i++) {
			field += "    " + key[i] + ": " + "{name: \"" + col[i] + "\", key: \"" + key[i] + "\"},\r\n";
		}
		String result = ""
				+ model
				+ "\r\n"
				+ field
				+ "}\r\n"
				+ "\r\n"
				+ "export const Model_" + name + " = {\r\n"
				+ "    Field,\r\n"
				+ "    " + name + "\r\n"
				+ "}";
		String dir = "E:\\Hoc Tap\\MoHinhPhanLop\\WebForm\\QLTour\\ql-tour_du_lich\\src\\Model\\";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(dir + name + ".js"));
			writer.write(result);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		String dir = System.getProperty("user.dir");
////		System.out.println(dir);
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			System.out.println("name");
//			String name = sc.nextLine();
//			if(name.contentEquals("exit"))
//				break;
//			System.out.println("col");
//			String[] col = sc.nextLine().split(";");
//			System.out.println("key");
//			String[] key = sc.nextLine().split(";");
//			System.out.println("value");
//			String[] value = sc.nextLine().split(";");
//			createModel(name, col, key, value);
//			
//		}
//		sc.close();
		System.out.println(DAO_TrangThai.search(new TrangThai("Code1", null)));
	}
}
