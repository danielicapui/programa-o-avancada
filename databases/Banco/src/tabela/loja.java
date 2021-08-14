package tabela;

import java.io.FileNotFoundException;
public class loja 
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		//Faz conexão
		Conexao con= new Conexao();
		String s1="categorias";
		String p1="produtos";
		con.drop(p1);
		con.drop(s1);
		con.createTable(s1,"categoria_id integer primary key, categoria text");
		String p="produto_id serial,nome text,valor real,categoria_id integer,constraint fk_categorias foreign key (categoria_id) references categorias(categoria_id)";
		con.createTable(p1,p);
		//Adiciona em tabela

		con.fileInto("src/tabela/categorias",s1,"categoria_id,categoria");
		con.fileInto("src/tabela/produtos",p1,"nome,valor,categoria_id");
		System.out.println("Buscando categorias...Concluido!");
		con.mostrarBusca(con.busca("select * from categorias"));
		
		System.out.println("Buscando produtos...Concluido!");
		con.mostrarBusca(con.busca("select * from produtos"));		
	}

}
