package co.istad;

import co.istad.controller.MenuController;
import co.istad.controller.ProductController;
import co.istad.dto.CreateProductDto;
import co.istad.dto.UpdateProductDto;
import co.istad.util.Singleton;

import java.util.Scanner;

import static java.lang.System.exit;

public class MainApplication {
    private final Scanner scanner;
    private final MenuController menuController;
    private final ProductController productController;

    public MainApplication() {
        scanner = Singleton.scanner();
        menuController = Singleton.menuController();
        productController = Singleton.productController();
    }

    public void run() {
        while(true) {
            menuController.index();
            System.out.println("Welcome to PMS");
            System.out.print("Enter option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> productController.index();
                case 2 -> {
                    System.out.println("Create Product");
                    System.out.print("Input Product ID: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    System.out.print("Input Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Input Product Qty: ");
                    Integer qty = Integer.parseInt(scanner.nextLine());
                    System.out.print("Input Product Price: ");
                    Double price = Double.parseDouble(scanner.nextLine());
                    CreateProductDto createProductDto = new CreateProductDto(id, name, qty, price);
                    productController.createProduct(createProductDto);
                    System.out.println("Product has been added!");
                }
                case 3 -> {
                    System.out.println("Update Product");
                    System.out.print("Input ID to update: ");
                    String id = scanner.nextLine();
                    System.out.print("Input Product Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Input Product Qty: ");
                    Integer newQty = Integer.parseInt(scanner.nextLine());
                    System.out.print("Input Product Price: ");
                    Double newPrice = Double.parseDouble(scanner.nextLine());
                    UpdateProductDto updateProductDto = new UpdateProductDto(newName, newQty, newPrice);
                    productController.updateProduct(id, updateProductDto);
                    System.out.println("Product has been updated!");
                }
                case 4 -> {
                    System.out.print("Input ID to delete: ");
                    Long foundId = Long.parseLong(scanner.nextLine());
                    productController.deleteById(foundId);
                    System.out.println("Delete Product ID: " + foundId + " Successfully");
                }
                case 5 -> exit(0);
                default -> throw new IllegalArgumentException();
            }
        }

    }
    public static void main(String[] args) {
        new MainApplication().run();
    }
}
