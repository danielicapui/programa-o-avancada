from utills import *
#iniciando arquivo
conn,cur=start("filial")
##criando tabelas
produtos_p="produto_id integer primary key autoincrement,nome text,valor real,categoria_id integer,constraint fk_categorias foreign key (categoria_id) references categorias(categoria_id)"
criarTabela("categorias","categoria_id integer primary key, categoria text")
criarTabela("produtos",produtos_p)
#carregando arquivos
categorias=loadData("categorias.txt")
produtos=loadData("produtos.txt")
insertInto("categorias","categoria_id,categoria",categorias)
insertInto("produtos","nome,valor,categoria_id",produtos)
buscaTabela("categorias")
buscaTabela("produtos")
conn.commit()
conn.close()