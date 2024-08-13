import java.util.Stack;

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
                if (novoNo.getValor() == atual.getValor()){
                    return;
                }
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
    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack(); // общий стек для значений дерева
        globalStack.push(raiz);
        int gaps = 32; // начальное meaning расстояния между elementми
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack localStack = new Stack(); // локальный стек для задания потомков element
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                No temp = (No) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.getValor()); // выводим его meaning в консоли
                    localStack.push(temp.getEsq()); // соохраняем в локальный стек, наследники текущего element
                    localStack.push(temp.getDir());
                    if (temp.getEsq() != null ||
                            temp.getDir() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между elementми каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }
}
