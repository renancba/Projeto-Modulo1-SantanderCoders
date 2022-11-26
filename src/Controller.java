import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Controller {

    private Integer idControl;

    private HashMap<Integer, Produto> produtos;

    public Controller() {
        this.idControl = 0;
        this.produtos = new HashMap<Integer, Produto>();
    }
    public Integer adicionaProduto(String nome, Integer quant, Double preco){
        if (nome.isEmpty() || quant<0 || preco<0) return -1;
        Produto produto = new Produto(nome, quant, preco, idControl);
        produtos.put(idControl, produto);
        idControl++;
        return idControl-1;
    }

    public String listaProdutos(){
        String lista = "";
        for (Integer id : produtos.keySet()){
            lista += id + " - " + produtos.get(id).getNome() + "\n";
        }
        return lista;
    }

    public boolean editaProduto(Integer idProduto, String nome, Integer quant, Double preco){
        if (nome.isEmpty() || quant<0 || preco<0) return false;
        produtos.get(idProduto).setNome(nome);
        produtos.get(idProduto).setQuantidade(quant);
        produtos.get(idProduto).setPreco(preco);
        return true;
    }

    public boolean compraProduto(List<Integer> idProduto, List<Integer> quant){
        for (int i = 0; i < idProduto.size(); i++) {
            if(!produtos.containsKey(idProduto.get(i)) || quant.get(i)<1) return false;
            produtos.get(idProduto.get(i)).setQuantidade(produtos.get(idProduto.get(i)).getQuantidade()-quant.get(i));
        }
        return true;
    }

    public Double calculaPrecoTotal(List<Integer> idProdutos, List<Integer> quantidades){
        Double valorTotal = 0.0;
        for (int i = 0; i < idProdutos.size(); i++) {
            if(produtos.containsKey(idProdutos.get(i))) valorTotal += produtos.get(idProdutos.get(i)).getPreco()*quantidades.get(i);
        }
        return valorTotal;
    }

    public boolean verificaQuantidade(Integer idProduto, Integer quant){
        if (!produtos.containsKey(idProduto) || quant<1) return false;
        if(produtos.get(idProduto).getQuantidade()<quant) return false;
        return true;
    }
    public String buscaProduto(String nome){
        String listaRetorno = "";
        ArrayList<String> nomesProdutos = new ArrayList<>();
        for (int i = 0; i < produtos.values().size(); i++) {
            nomesProdutos.add(produtos.get(i).getNome());
        }
        for (int j = 0; j < nomesProdutos.size(); j++) {
            if(nomesProdutos.get(j).contains(nome)){
                listaRetorno += j + " - " + produtos.get(j).getNome() + "\n";
            }
        }
        if(listaRetorno.isEmpty()) return "Produto não encontrado";
        return listaRetorno;
    }

    public void excluirProduto(Integer idProduto){
        produtos.remove(idProduto);
    }
    public boolean contemProduto(Integer id){
        return produtos.containsKey(id);
    }

    public Integer getTamanho(){
        return produtos.keySet().size();
    }


}
