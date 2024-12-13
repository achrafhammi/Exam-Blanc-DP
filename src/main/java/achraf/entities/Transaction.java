package achraf.entities;

import java.time.LocalDate;

public class Transaction {
    private String id;
    private LocalDate date;
    private Double montant;
    private TransactionType transactionType;

    public Transaction(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.montant = builder.montant;
        this.transactionType = builder.transactionType;
    }

    public String getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public Double getMontant() {
        return montant;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private LocalDate date;
        private double montant;
        private TransactionType transactionType;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder montant(double montant) {
            this.montant = montant;
            return this;
        }

        public Builder transactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }

    @Override
    public String toString() {
        return "Transaction " + id + " [" + date + ", " + montant + ", " + transactionType + "]";
    }
}
