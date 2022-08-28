package com.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="account")
@JsonInclude(value = Include.NON_NULL)
public class Account implements Serializable {
  private static final long serialVersionUID = 3252591505029724236L;

  @Id
  private Long accountnumber;
  
  private Long locationcode;

  private String holdername;

  private Double accountbalance;
  
  private Boolean activestatus;
  
  private String city;
  
  private String state;
  
  private String country;
  
  private String currency;
  
  private String bankname;
  
  @Transient
  private List<FundTransferResponse> transactionList = new ArrayList<FundTransferResponse>();

  public Account() {}

  

@Override
public int hashCode() {
	return Objects.hash(accountbalance, accountnumber, activestatus, bankname, city, country, currency, holdername,
			locationcode, state);
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Account other = (Account) obj;
	return Objects.equals(accountbalance, other.accountbalance) && Objects.equals(accountnumber, other.accountnumber)
			&& Objects.equals(activestatus, other.activestatus) && Objects.equals(bankname, other.bankname)
			&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
			&& Objects.equals(currency, other.currency) && Objects.equals(holdername, other.holdername)
			&& Objects.equals(locationcode, other.locationcode) && Objects.equals(state, other.state);
}



public Account(Long accountnumber, Long locationcode, String holdername, Double accountbalance, Boolean activestatus,
		String city, String state, String country, String currency, String bankname) {
	super();
	this.accountnumber = accountnumber;
	this.locationcode = locationcode;
	this.holdername = holdername;
	this.accountbalance = accountbalance;
	this.activestatus = activestatus;
	this.city = city;
	this.state = state;
	this.country = country;
	this.currency = currency;
	this.bankname = bankname;
}




public List<FundTransferResponse> getTransactionList() {
	return transactionList;
}



public void setTransactionList(List<FundTransferResponse> transactionList) {
	this.transactionList = transactionList;
}



public Long getAccountnumber() {
	return accountnumber;
}



public void setAccountnumber(Long accountnumber) {
	this.accountnumber = accountnumber;
}



public Long getLocationcode() {
	return locationcode;
}



public void setLocationcode(Long locationcode) {
	this.locationcode = locationcode;
}



public String getHoldername() {
	return holdername;
}



public void setHoldername(String holdername) {
	this.holdername = holdername;
}



public Double getAccountbalance() {
	return accountbalance;
}



public void setAccountbalance(Double accountbalance) {
	this.accountbalance = accountbalance;
}



public Boolean getActivestatus() {
	return activestatus;
}



public void setActivestatus(Boolean activestatus) {
	this.activestatus = activestatus;
}



public String getCity() {
	return city;
}



public void setCity(String city) {
	this.city = city;
}



public String getState() {
	return state;
}



public void setState(String state) {
	this.state = state;
}



public String getCountry() {
	return country;
}



public void setCountry(String country) {
	this.country = country;
}



public String getCurrency() {
	return currency;
}



public void setCurrency(String currency) {
	this.currency = currency;
}



public String getBankname() {
	return bankname;
}



public void setBankname(String bankname) {
	this.bankname = bankname;
}



public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
	  private Long accountnumber;
	  
	  private Long locationcode;

	  private String holdername;

	  private Double accountbalance;
	  
	  private Boolean activestatus;

    public Builder setaccountnumber(Long accountnumber) {
      this.accountnumber = accountnumber;
      return this;
    }

    public Builder setlocationcode(Long locationcode) {
      this.locationcode = locationcode;
      return this;
    }

    public Builder setaccountbalance(Double accountbalance) {
      this.accountbalance = accountbalance;
      return this;
    }

    public Builder setholdername(String holdername) {
      this.holdername = holdername;
      return this;
    }
    
    public Builder setactivestatus(Boolean activestatus) {
        this.activestatus = activestatus;
        return this;
      }

//    public Account build() {
//      return new Account( accountnumber,  holdername, accountbalance,  activestatus, locationcode);
//    }
  }

}
