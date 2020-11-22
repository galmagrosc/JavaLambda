
import java.util.*;
import java.util.stream.Collectors;


public class LambdaExample {
	/**
	* Change from GIT
	* 2º Change from Eclipse
	* 3º change from eclipse: newBranchEclipse1
	* 5º change in newBranchEclipse1
	* 8º change from eclipse to test merging
	* 7º change from GitHub to test merging
	* 4º change in master
	* 11º change in master
	* 9º change in newBranchEclipse1
	* 12º change in newBranchEslipse1
	*/
	private List<Map<String, Object>>  getObras() {
		//Mat1
		Map<String, Object> mat1 = new HashMap<>();
		mat1.put("matCode", "mata11code");
		mat1.put("matDesc", "mata11desc");
		
		Map<String, Object> mat2 = new HashMap<>();
		mat2.put("matCode", "matb22code");
		mat2.put("matDesc", "matb22desc");
		
		List<Map<String, Object>> listaMat1 = new ArrayList<>();
		listaMat1.add(mat1);
		listaMat1.add(mat2);	
		
		//Mat2
		Map<String, Object> mat3 = new HashMap<>();
		mat3.put("matCode", "matb22code"); //matb3code
		mat3.put("matDesc", "mata33desc");
		
		Map<String, Object> mat4 = new HashMap<>();
		mat4.put("matCode", "matb44code");
		mat4.put("matDesc", "matb44desc");
		
		List<Map<String, Object>> listaMat2 = new ArrayList<>();
		listaMat2.add(mat3);
		listaMat2.add(mat4);		
		
		//obra1
		Map<String, Object> obra1 = new HashMap<>();
		obra1.put("obraCode", "obra11code");
		obra1.put("obraMat", listaMat1);
		//obra2
		Map<String, Object> obra2 = new HashMap<>();
		obra2.put("obraCode", "obra21code");
		obra2.put("obraMat", listaMat2);
		
		List<Map<String, Object>> listaObra = new ArrayList<>();
		listaObra.add(obra1);
		listaObra.add(obra2);
		
		
		return listaObra;
	}
	
	private void test() {
		List<Map<String, Object>> listaObra = getObras();
		System.out.println("test lambda: " + listaObra);
		
		//--------------1
		List<Map<String, Object>> lista = 
				listaObra.stream()
			.filter(m -> m.get("obraCode").equals("obra11code"))
			.collect(Collectors.toList());
		
		System.out.println("test lambda 1 LIsT: " + lista);
		
		//--------------2
		List<Object> listaMat2 = 
				listaObra.stream()
			.map(m -> m.get("obraMat")) 
			.collect(Collectors.toList());
		
		System.out.println("test lambda 2 LIsT: " + listaMat2);		
		
		//--------------3
		Map<String, Object> lista3 =				
				listaObra.stream()			
			.collect(Collectors.toMap(m -> (String)m.get("obraCode"), m -> m.get("obraMat")));
		
		System.out.println("test lambda 3 MAP: " + lista3);			
		
		//--------------3.1
		/*Map<String, Object> lista31 =				
				listaObra.stream()			
			//	.collect(Collectors.toMap(m -> (String)m.get("obraCode"), m -> (String)((Map<String, Object>)m.get("obraMat")).get("matCode")));
			//	.collect(Collectors.toMap(m -> (String)m.get("obraCode"), m -> (String)((Map<String, Object>)m.get("obraMat")).get("matCode")));
 		    //  .collect(Collectors.toMap(m -> (String)m.get("obraCode"), m -> (String)((List<String>)m.get("obraMat")).get(0)));
			//	.collect(Collectors.toMap(m -> (String)m.get("obraCode"), m -> (String)((List<String>)m.get("obraMat")).get(0)));
			.collect(Collectors.toMap(m -> (String)m.get("obraCode"), m -> m.get("obraMat")))
			.toStream()
			.collect(Collectors.toMap(m -> (String)m.get("obraCode"), m -> m.get("obraMat")));*/
		//final Map<String, Object> mapa31 = new HashMap<>();	
				
/*		lista3.forEach((k, v) -> {
			System.out.println(k + ":" + v); //(List<Map<String, Object>>)
			//mapa31.put(k, value)	.(List<Map<String, Object>>)
			Map<String, Object> mapa31 = ((List<Map<String, Object>>)v)
			.stream()
			.collect(Collectors.toMap(m -> k, m -> (String)((Map<String, Object>)m).get("matCode")));
			
			System.out.println("test lambda 31 MAP: " + mapa31);
		}); //System.out.println((k + ":" + v)))
*/		
		//System.out.println("test lambda 3.1 MAP: " + lista31);				
		
		//--------------4
		List<Object> listaMat4 = 
				listaMat2.stream()
			.flatMap(m -> ((List<Object>)m).stream())
			.collect(Collectors.toList());
		
		System.out.println("test lambda 4 LIsT: " + listaMat4);
		
		//--------------2 + 4 together:
		List<Object> listaMat5 = 
				listaObra.stream()
			.map(m -> m.get("obraMat"))
			.flatMap(m -> ((List<Object>)m).stream())
			.collect(Collectors.toList());
		
		System.out.println("test lambda 2+4 LIsT: " + listaMat5);
		
		//--------------6
		Map<Object, Object> mapa6 = 
				listaObra.stream()
			.map(m -> m.get("obraMat"))
			.flatMap(m -> ((List<Object>)m).stream())
			.collect(Collectors.toMap(m -> ((Map<String, Object>)m).get("matCode"),
									  m -> ((Map<String, Object>)m).get("matDesc"),
									  (v1, v2) -> {
							                 System.out.println("duplicate key found!");
							                 return v1;
							             }
					));
		
		System.out.println("test lambda 6 MAP: " + mapa6);
		
	}
	
	public static void main(String[] args) {
		LambdaExample ex = new LambdaExample();
		ex.test();
		
	}

}
