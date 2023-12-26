# Java Developer

- This Repository containing the source code of advanced subjects about Java versions 8 / 11 / 17 / 21

## 🚀 Started

These instructions will allow you to get a copy of the project running on your local machine for development and testing purposes.

Consulte **[Implantação](#-implanta%C3%A7%C3%A3o)** para saber como implantar o projeto.

### 📋 Pre Requirements

  It is necessary instalation of JDK / OpenJDK(Free) acording with specific subjects 

### 🔧 Links para as versões do Projeto

	- Subjects:
	  Section 1: - [Lambda](https://github.com/CarlosRobertoMedeiros/repo-java-developer/tree/main/Java%208/advanced-techniques-java-8/src/main/java/br/com/roberto/advancedtechniquesjava8/a/lambda)
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
                    
	  
	  Section 2: - [Streams](https://github.com/CarlosRobertoMedeiros/repo-java-developer/tree/main/Java%208/advanced-techniques-java-8/src/main/java/br/com/roberto/advancedtechniquesjava8/b/streams)
		a/Initial Example
		b/Stream Laziness
		c/Creating Streams
                    Creating Array Streams
                    Creating a Stream from a Collection
                    Creating a Stream from a Collection example 2
                    Creating a Stream With Stream.of()
                    Creating a Stream from File
                    Creating Infinity Stream
                    Creating Infinity Stream example 2
		d/Terminal Operators
                    Example Using Count() Min() and Max()
                    Example Using FindAny() and FindFirst()
                    Example Using AnyMatch(), AllMatch() and NoneMatch()
                    Example Using forEach()
                    Example Using reduce()
                    Example Using BinaryOperator
                    Example Using Function With Reduce()


## 🖇️ Collaboration

  Under constructor

## 📌 Version Control Structure

We using [SemVer](http://semver.org/) for version control

## ✒️ Author

* **Carlos Roberto - dev ** - *Work and Documentation*


## 📄 Licence

   All theses projects are for study only.

## 🎁 Gratitude

* Thank you God for give-me the oportunity to improve my habilities in software development;
---
⌨️ With ❤️ por [Carlos Roberto] 😊