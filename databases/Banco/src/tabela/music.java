package tabela;

import java.io.FileNotFoundException;

public class music {

	public static void main(String[] args) throws FileNotFoundException 
	{
		//Faz conexão
		Conexao con= new Conexao();
		String s1="musicas";
		con.drop(s1);
		con.createTable(s1,"tracks text,plays integer");
		//Adiciona em tabela
		con.fileInto("src/tabela/musicas",s1,"tracks,plays");
		System.out.println("Buscando categorias...Concluido!");
		con.mostrarBusca(con.busca("select tracks from "+s1));
	}

}
