import java.util.*;

class Request {
}

class Response {
}

class Servlet {
    public void process(Request request, Response response) {
        System.out.println("Servlet is processing");
    }
}

interface Filter {
    void doFilter(Request request, 
        Response response, FilterChain filterChain);
}

class ConcreteFilter1 implements Filter {
    public void doFilter(Request request, Response response,
        FilterChain filterChain) {
        System.out.println("ConcreteFilter1: before chain");
        filterChain.doFilter(request, response);
        System.out.println("ConcreteFilter1: before chain");
    }
}

class ConcreteFilter2 implements Filter {
    public void doFilter(Request request, Response response,
        FilterChain filterChain) {
        System.out.println("ConcreteFilter2: before chain");
        filterChain.doFilter(request, response);
        System.out.println("ConcreteFilter2: before chain");
    }
}

class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    private int where = 0;
    private Servlet servlet;

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public FilterChain addServlet(Servlet servlet) {
        this.servlet = servlet;
        return this;
    }

    public void doFilter(Request request, Response response) {
        if (where < filters.size()) {
            filters.get(where++).doFilter(request, response, this);
        } else 
            servlet.process(request, response);
    }

    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new ConcreteFilter1())
            .addFilter(new ConcreteFilter2())
            .addServlet(new Servlet())
            .doFilter(new Request(), new Response());
    }
}

