package com.example.myapplication;

import java.util.Scanner;

public class PaymentMethodScreen {

    // Renamed to avoid conflict with existing PaymentMethod class
    public enum PaymentOption {
        GCASH("GCash"),
        MASTERCARD("Mastercard"),
        VISA("VISA");

        private final String displayName;

        PaymentOption(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private static final double COST_OF_PURCHASE = 690.0;
    private static final double TAX_FEE = 5.0;
    private static final double TOTAL = COST_OF_PURCHASE + TAX_FEE; // 695

    private PaymentOption selectedMethod = PaymentOption.GCASH; // default selected

    // ========== Selection Functions ==========

    public void selectGCash() {
        selectedMethod = PaymentOption.GCASH;
        System.out.println("✓ GCash selected");
    }

    public void selectMastercard() {
        selectedMethod = PaymentOption.MASTERCARD;
        System.out.println("✓ Mastercard selected");
    }

    public void selectVisa() {
        selectedMethod = PaymentOption.VISA;
        System.out.println("✓ VISA selected");
    }

    // ========== Utility Functions ==========

    public PaymentOption getSelectedMethod() {
        return selectedMethod;
    }

    public double getCostOfPurchase() {
        return COST_OF_PURCHASE;
    }

    public double getTaxFee() {
        return TAX_FEE;
    }

    public double getTotal() {
        return TOTAL;
    }

    public void showSummary() {
        System.out.println("\n---------- SUMMARY ----------");
        System.out.printf("Cost of purchase : %.0fP%n", getCostOfPurchase());
        System.out.printf("Tax fee          : %.0fP%n", getTaxFee());
        System.out.println("-----------------------------");
        System.out.printf("Total            : %.0fP%n", getTotal());
        System.out.println("Selected method  : " + selectedMethod.getDisplayName());
        System.out.println("-----------------------------");
    }

    // ========== CONTINUE Button ==========

    public void continuePayment() {
        if (selectedMethod == null) {
            System.out.println("Please select a payment method first.");
            return;
        }

        System.out.println("\nProcessing payment...");
        System.out.println("Method : " + selectedMethod.getDisplayName());
        System.out.printf("Amount : %.0fP%n", getTotal());

        switch (selectedMethod) {
            case GCASH:
                processGCashPayment();
                break;
            case MASTERCARD:
                processMastercardPayment();
                break;
            case VISA:
                processVisaPayment();
                break;
        }
    }

    private void processGCashPayment() {
        System.out.println("→ Redirecting to GCash...");
        System.out.println("Payment successful via GCash!");
    }

    private void processMastercardPayment() {
        System.out.println("→ Opening Mastercard payment form...");
        System.out.println("Payment successful via Mastercard!");
    }

    private void processVisaPayment() {
        System.out.println("→ Opening VISA payment form...");
        System.out.println("Payment successful via VISA!");
    }

    // ========== Demo ==========
    public static void main(String[] args) {
        PaymentMethodScreen screen = new PaymentMethodScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== PAYMENT METHOD =====");
        screen.showSummary();

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Select GCash");
            System.out.println("2. Select Mastercard");
            System.out.println("3. Select VISA");
            System.out.println("4. Show Summary");
            System.out.println("5. CONTINUE");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    screen.selectGCash();
                    break;
                case 2:
                    screen.selectMastercard();
                    break;
                case 3:
                    screen.selectVisa();
                    break;
                case 4:
                    screen.showSummary();
                    break;
                case 5:
                    screen.continuePayment();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}