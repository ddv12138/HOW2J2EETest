package CRUD.JavaBean;

import java.util.UUID;

public class Product {
    String id, name, cid;
    Double price;

    public Product(String name, Double price) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"cid\":\"")
                .append(cid).append('\"');
        sb.append(",\"price\":")
                .append(price);
        sb.append('}');
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
