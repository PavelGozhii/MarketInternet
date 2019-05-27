package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "basket")
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String idUser;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "basket_goods",
            joinColumns = { @JoinColumn(name = "id_basket")},
            inverseJoinColumns = {@JoinColumn(name = "id_goods")}
    )
    Set<Good> goods = new HashSet<>();

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setGoods(Set<Good> goodSet) {
        this.goods = goodSet;
    }
}

