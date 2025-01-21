package org.example.borrowing.serviceImpl;

import org.example.borrowing.dto.BorrowingDTO;
import org.example.borrowing.entity.Borrowing;
import org.example.borrowing.repository.BorrowingRepository;
import org.example.borrowing.rest.ServiceClient;
import org.example.borrowing.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private ServiceClient serviceClient;

    @Override
    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    @Override
    public Borrowing findById(Long id) {
        return borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing not found"));
    }

    @Override
    public Borrowing save(Borrowing borrowing) {
        String userType = serviceClient.getUserType(borrowing.getUserId());
        int maxBorrowingLimit = "PREMIUM".equals(userType) ? 7 : 5;
        List<Borrowing> allBorrowings = borrowingRepository.findAll();

        long borrowingCount = allBorrowings.stream()
                .filter(b -> b.getUserId().equals(borrowing.getUserId()))
                .count();


        boolean alreadyBorrowed = allBorrowings.stream()
                .anyMatch(b -> b.getUserId().equals(borrowing.getUserId()) &&
                        b.getBookId().equals(borrowing.getBookId()));

        if (alreadyBorrowed) {
            throw new RuntimeException("Erreur : Déjà réservé.");
        }

        boolean bookAlreadyBorrowed = allBorrowings.stream()
                .anyMatch(b -> b.getBookId().equals(borrowing.getBookId()));

        if (bookAlreadyBorrowed) {
            throw new RuntimeException("Erreur : Livre déjà emprunté.");
        }

        return borrowingRepository.save(borrowing);
    }

    @Override
    public void deleteById(Long id) {
        borrowingRepository.deleteById(id);
    }

}
