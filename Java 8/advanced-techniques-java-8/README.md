# Java Developer

Repositorio contendo código fonte referente a assuntos avançados de Java 8 

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

Consulte **[Implantação](#-implanta%C3%A7%C3%A3o)** para saber como implantar o projeto.

### 📋 Pré-requisitos

Necessário a instalação da JDK / OpenJDK(Free) de acordo com a versão do projeto 

### 🔧 Links para as versões do Projeto

	- Assuntos:
	  Section 1: Lambda 
		Introduction
        a/BasicLambda
            Implements Functional interface before Java 8
            Implements Functional interface Using Lambda Expression
        b/CustomFunctionalIterface
            Implements Custom Functional Interface Examples
            Implements Predicate<T> Example
                Predicate<T>     boolean test(T t)              when you want to filter/match
        c/FunctionalInterface_from_API - Predicate and BiPredicate
                Predicate<T>          boolean test(T t)         when you want to filter/match
                BiPredicate<T, U>     boolean test(T t, U u)
        c/FunctionalInterface_from_API - Supplier (Fornecedor)
                Supplier<T>           T get()                   when you want to supply values without any inputs
        c/FunctionalInterface_from_API - Consumer (Consumidor)
                Consumer<T>           void accept(T t)          use the parameter but not interested in the return value
                BiConsumer<T,U>       void accept(T t, U u) 
        c/FunctionalInterface_from_API - Function 
                Function<T,R>         R apply(T t)              transform the input into an output (types can be different) 
                BiFunction<T,U,R>     R apply(T t, U u)
        c/FunctionalInterface_from_API - UnaryOperator 
                UnaryOperator<T>      T apply(T t)              transform the input into an output (types are the same) 
                BinaryOperator<T>     T apply(T t1, T t2)
        d/LambdaFinalAndEfectivelyFinal
                Em Java, ao usar lambdas, há uma restrição relacionada à captura de variáveis locais externas à expressão lambda. 
                Essas variáveis locais precisam ser efetivamente finais ou finais para serem utilizadas dentro de uma expressão lambda. 
                Esse requisito é conhecido como "efetivamente final" ou "final-effectively".
                Uma variável é considerada "efetivamente final" quando ela é atribuída apenas uma vez e nunca é modificada depois disso. 
                Isso se aplica mesmo que a palavra-chave final não seja explicitamente usada.
                A ideia é garantir que a variável não mude seu valor após ser capturada pela expressão lambda, o que pode causar problemas de concorrência e degradação no desempenho.
		e/MethodReferences
                Consumer<T>           void accept(T t)          use the parameter but not interested in the return value     
                # There are four different styles/types:
                    1. Bound   - instance known at compile-time
                    2. Unbound - instance provided at runtime
                    3. Static
                    4. Constructor
                # With method reference, context is key !
                    The functional interface type being created is hugely important in determining context
                # The compiler turns tour method references into lambdas in the backgroud
		e/MethodReferences
                boundMethodReference
                    Supplier<T>           T get()                   when you want to supply values without any inputs
                    Predicate<T>          boolean test(T t)         when you want to filter/match
                unboundMethodReference
                    Function<T,R>         R apply(T t)              transform the input into an output (types can be different) 
                    BiFunction<T,U,R>     R apply(T t, U u)
                staticMethodReference
                    Consumer<T>           void accept(T t)          use the parameter but not interested in the return value
                constructorMethodReference
                    Function<T,R>         R apply(T t)              transform the input into an output (types can be different)
                    Supplier<T>           T get()                   when you want to supply values without any inputs
        f/MethodReferenceAndContext
                    Supplier<T>           T get()                   when you want to supply values without any inputs
                    Function<T,R>         R apply(T t)              transform the input into an output (types can be different)
                    BiFunction<T,U,R>     R apply(T t, U u)
                    
	  
	  Section 2: Streams
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		

## 🖇️ Colaborando

Por favor, leia o [COLABORACAO.md](https://gist.github.com/usuario/linkParaInfoSobreContribuicoes) para obter detalhes sobre o nosso código de conduta e o processo para nos enviar pedidos de solicitação.

## 📌 Versão

Nós usamos [SemVer](http://semver.org/) para controle de versão. Para as versões disponíveis, observe as [tags neste repositório](https://github.com/suas/tags/do/projeto). 

## ✒️ Autores

* **Carlos Roberto - dev ** - *Trabalho e Documentação*


## 📄 Licença

Este projeto está sob a licença (sua licença) - veja o arquivo [LICENSE.md](https://github.com/usuario/projeto/licenca) para detalhes.

## 🎁 Expressões de gratidão

* Obrigado Deus pela execução do projeto;

---
⌨️ com ❤️ por [Carlos Roberto] 😊