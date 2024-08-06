public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor);
        if(this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;
            No pai = null;
            boolean esquerda = false;
            while(atual != null) {
                if(novoNo.getValor() < atual.getValor()) {
                    pai = atual;
                    atual = atual.getEsq();
                    esquerda = true;
                } else {
                    pai = atual;
                    atual = atual.getDir();
                    esquerda = false;
                }
            }
            if(esquerda) {
                pai.setEsq(novoNo);
            } else {
                pai.setDir(novoNo);
            }
        }
    }

    public No remover(No no,int valor){
        if (no == null){
            return null;
        }
        if (firstcase(no,valor)){
            return null;
        }
        if (secondcase(no,valor)){
            if(no.equals(this.getRaiz())){
                this.raiz = no.getDir();
                return no;
            }
            return no.getDir();
        }
        if (thirdcase(no,valor)){
            if(no.equals(this.getRaiz())){
                this.raiz = no.getEsq();
                return no;
            }
            return no.getEsq();
        }
        if (fourthcase(no,valor)){
            No noauxiliar = menor(no.getDir());
            remover(no,noauxiliar.getValor());
            no.setValor(noauxiliar.getValor());
            return no;
        }
        no.setEsq(remover(no.getEsq(),valor));
        no.setDir(remover(no.getDir(),valor));
        return no;
    }

    public No getRaiz() {
        return this.raiz;
    }

    public void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getValor());
        preOrdem(no.getEsq());
        preOrdem(no.getDir());
    }

    public void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsq());
        System.out.println(no.getValor());
        emOrdem(no.getDir());
    }

    public void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsq());
        posOrdem(no.getDir());
        System.out.println(no.getValor());
    }

    private boolean firstcase(No no, int valor){
        return (no.getEsq() == null && no.getDir() == null && no.getValor() == valor);
    }
    private boolean secondcase(No no, int valor){
        return (no.getEsq() == null && no.getDir() != null && no.getValor()== valor);
    }
    private boolean thirdcase(No no,int valor){
        return (no.getEsq()!=null && no.getDir() == null && no.getValor()== valor);
    }
    private boolean fourthcase(No no,int valor){
        return (no.getEsq()!=null && no.getDir() != null && no.getValor()== valor);
    }
    public No menor(No no){
        if(no.getEsq() == null) {
            return no;
        }
        return menor(no.getEsq());
    }
    public No maior(No no){
        if(no.getDir() == null) {
            return no;
        }
        return maior(no.getDir());
    }
}