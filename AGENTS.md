# CS2430 Exercise 1 - Mobile Shop App

Build a complete Android mobile shop app for CS2430 Mobile Computing Exercise 1.

## Assignment requirements
- Use Kotlin Jetpack Compose.
- Do not use Flutter.
- Do not use Firebase.
- Do not use a backend.
- Do not use a database.
- Store products in a simple Kotlin list.
- Allow users to pick/add items.
- Display the shopping bag/cart total.
- Include a README.pdf with:
  - setup instructions
  - run instructions
  - screenshots
  - exact project structure
- Final ZIP must contain Kotlin files, images/assets, and README.pdf.
- App must work on Android emulator.
- App should also work on an actual Android phone for bonus marks.

## Full marks target
Aim for:
- 10/10 Kotlin programming
- 10/10 functionality
- 10/10 design/responsive UI

## Required app features
- Home screen
- Product list/grid
- Product cards with image, name, category, price, and Add to Bag button
- Product detail screen
- Cart/shopping bag screen
- Add to cart
- Remove from cart
- Increase/decrease quantity
- Subtotal, tax, and grand total
- Empty cart state
- Checkout success dialog
- Cart badge count
- Material 3 UI
- Responsive layout
- Clean professional theme
- No crashes

## Code organization
Use this structure:
- model/
- data/
- viewmodel/
- ui/components/
- ui/screens/
- ui/navigation/
- ui/theme/

## Money handling
Use integer cents for prices and totals, not raw Double calculations.

## Verification before final response
Before finishing:
- Run the Gradle build.
- Fix all compile errors.
- Verify app launches.
- Verify cart total changes correctly.
- Verify README.md exists.
- List remaining manual tasks: screenshots, README.pdf export, GitHub upload, ZIP submission.

## Do not
- Do not leave TODOs.
- Do not create a web-only app.
- Do not copy an online Nike/store template.
- Do not use database/backend/login/payment.