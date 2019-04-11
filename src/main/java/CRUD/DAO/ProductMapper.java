package CRUD.DAO;

import CRUD.JavaBean.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    @Select("select * from Product")
    List<Product> ListProduct();
}
