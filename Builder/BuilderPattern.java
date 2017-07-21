class Product {
    private String part1;
    private String part2;

    public String getPart1() {
        return part1;
    }
    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }
    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String toString() {
        return String.format("Product{part1=%s, part2=%s}",
            part1, part2);
    }
}

interface Builder {
    public void buildPart1();
    public void buildPart2();
    public Product build();
}

class ConcreteBuilder1 implements Builder {
    private Product product = new Product();
    
    public void buildPart1() {
        product.setPart1("built by ConcreteBuilder1");
    }

    public void buildPart2() {
        product.setPart2("built by ConcreteBuilder1");
    }

    public Product build() {
        return product;
    }
}

class ConcreteBuilder2 implements Builder {
    private Product product = new Product();
    
    public void buildPart1() {
        product.setPart1("built by ConcreteBuilder2");
    }

    public void buildPart2() {
        product.setPart2("built by ConcreteBuilder2");
    }

    public Product build() {
        return product;
    }
}


class Director {
    public Product constructProduct(Builder builder) {
        builder.buildPart2();
        builder.buildPart1();
        return builder.build();
    }
}


class BuilderPattern {
    public static void main(String[] args) {
        Director director = new Director();
        Builder builder1 = new ConcreteBuilder1();
        Builder builder2 = new ConcreteBuilder2();

        System.out.println(director.constructProduct(builder1));
        System.out.println(director.constructProduct(builder2));
    }
}

