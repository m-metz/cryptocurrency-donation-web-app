package com.group13.cryptocurrencywebapp.repository;

import org.springframework.stereotype.Repository;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * Class Name: CryptoCurrencyDonationRepository
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Interface for communication with MySQL database. Used to update and query
 * information about CryptoCurrencyDonation entity
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Repository
public interface CryptoCurrencyDonationRepository extends JpaRepository<CryptoCurrencyDonation, Integer> {

    /**
     * Query for finding a CryptoCurrencyDonation by it's id.
     * 
     * @param donation_id to be used as search argument
     * @return CryptocurrencyDonation object, or null, wrapped in an Optional type
     *         of object.
     */
    Optional<CryptoCurrencyDonation> findByDonationId(int donation_id);

    /**
     * Query for finding all CryptoCurrencyDonation objects related to a specific
     * userid
     * 
     * @param userid to be used as search argument
     * @return List of all CryptocurrencyDonation found related to the user or empty
     *         list.
     */
    List<CryptoCurrencyDonation> findAllByDonorUserId(String userid);

    /**
     * Query for finding all CryptoCurrencyDonation objects containing a specific
     * status mask.
     * 
     * @param string to be used as "like" search argument for status field
     * @return List of all CryptocurrencyDonation found related to status or empty
     *         list if not found
     */
    List<CryptoCurrencyDonation> findByStatusContaining(String string);

}
