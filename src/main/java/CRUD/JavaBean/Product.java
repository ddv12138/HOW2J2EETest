package CRUD.JavaBean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Product
 *
 * @author
 */
public class Product implements Serializable {
    private String id;

    private String name;

    private Float price;

    private String cid;

    private Category category;

    private String categoryName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"price\":")
                .append(price);
        sb.append(",\"cid\":\"")
                .append(cid).append('\"');
        sb.append(",\"category\":")
                .append(category);
        sb.append(",\"categoryName\":\"")
                .append(categoryName).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(cid, product.cid) &&
                Objects.equals(category, product.category) &&
                Objects.equals(categoryName, product.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, cid, category, categoryName);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private static final long serialVersionUID = 1L;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

}