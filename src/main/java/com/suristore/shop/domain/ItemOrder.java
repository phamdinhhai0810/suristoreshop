package com.suristore.shop.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_order")
@AssociationOverrides({ @AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_id")),
		@AssociationOverride(name = "pk.product", joinColumns = @JoinColumn(name = "product_id")), })
public class ItemOrder extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PK pk = new PK();

	private static class PK implements Serializable {

		private static final long serialVersionUID = 1L;

		@ManyToOne(fetch = FetchType.LAZY)
		private Order order;

		@ManyToOne(fetch = FetchType.LAZY)
		private Product product;

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

	}

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "soldPrice", nullable = false)
	private int soldPrice; // giá đã bán

	public ItemOrder() {

	}

	@Transient
	public Order getOrder() {
		return pk.getOrder();
	}

	public void setOrder(Order order) {
		pk.setOrder(order);
	}

	@Transient
	public Product getProduct() {
		return pk.getProduct();
	}

	public void setProduct(Product product) {
		pk.setProduct(product);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(int soldPrice) {
		this.soldPrice = soldPrice;
	}

	@Override
	public String toString() {
		return "{\"ItemOrder\":{" + "\"quantity\":\"" + quantity + "\"" + ", \"soldPrice\":\"" + soldPrice + "\""
				+ "}}";
	}
}
