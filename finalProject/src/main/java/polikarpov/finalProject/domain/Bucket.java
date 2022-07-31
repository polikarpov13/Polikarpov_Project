package polikarpov.finalProject.domain;

import java.sql.Date;
import java.util.Objects;

public class Bucket {
	private Integer id;
	private Integer userId;
	private Integer productId;
	private Date purchaseDate;
	
	public Bucket() { }

	public Bucket(Integer userId, Integer productId, Date purchaseDate) {
		this.userId = userId;
		this.productId = productId;
		this.purchaseDate = purchaseDate;
	}

	public Bucket(Integer id, Integer userId, Integer productId, Date purchaseDate) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.purchaseDate = purchaseDate;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productId, purchaseDate, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bucket other = (Bucket) obj;
		return Objects.equals(id, other.id) && Objects.equals(productId, other.productId)
				&& Objects.equals(purchaseDate, other.purchaseDate) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Bucket [id=" + id + ", userId=" + userId + ", productId=" + productId + ", purchaseDate=" + purchaseDate
				+ "]";
	}
	
}
