package com.group13.cryptocurrencywebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group13.cryptocurrencywebapp.entity.TaxReceipt;

/**
 * <pre>
 * Class Name: TaxReceiptRepository
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Interface for communication with MySQL database. Used to update and query
 * information about TaxReceipt entity
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Repository
public interface TaxReceiptRepository extends JpaRepository<TaxReceipt, Integer> {

}
