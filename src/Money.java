public class Money {
    private int c1, c5, c10, c20, c50, c100, c200, c500, c1000;

    // Creates an instance of the Money class with zero set for all denominations.
    public Money() {
        c1 = 0;
        c5 = 0;
        c10 = 0;
        c20 = 0;
        c50 = 0;
        c100 = 0;
        c200 = 0;
        c500 = 0;
        c1000 = 0;
    }

    // Creates an instance of the Money class with default denominations.
    public Money(String def) {
        this.c1 = 100;
        this.c5 = 100;
        this.c10 = 100;
        this.c20 = 50;
        this.c50 = 20;
        this.c100 = 10;
        this.c200 = 5;
        this.c500 = 5;
        this.c1000 = 5;
      }

    // Adds one count to the 1-peso coin.
    public void addC1() {
        this.c1++;
    }
    
    // Adds one count to the 5-peso coin.
    public void addC5() {
        this.c5++;
    }

    // Adds one count to the 10-peso coin.
    public void addC10() {
        this.c10++;
    }

    // Adds one count to the 20-peso coin.
    public void addC20() {
        this.c20++;
    }

    // Adds one count to the 50-peso bill.
    public void addC50() {
        this.c50++;
    }

    // Adds one count to the 100-peso bill.
    public void addC100() {
        this.c100++;
    }

    // Adds one count to the 200-peso bill.
    public void addC200() {
        this.c200++;
    }

    // Adds one count to the 500-peso bill.
    public void addC500() {
        this.c500++;
    }

    // Adds one count to the 1000-peso bill.
    public void addC1000() {
        this.c1000++;
    }

    // Calculates the total amount of the Money object based on its denominations.
    public int calculateTotal() {
        int total = 0;
    
        total += this.c1;
        total += this.c5 * 5;
        total += this.c10 * 10;
        total += this.c20 * 20;
        total += this.c50 * 50;
        total += this.c100 * 100;
        total += this.c200 * 200;
        total += this.c500 * 500;
        total += this.c1000 * 1000;
    
        return total;
      }

      // Returns the number of 1-peso coins in the Money object.
      public int getC1() {
          return c1;
      }

      // Returns the number of 5-peso coins in the Money object.
      public int getC5() {
          return c5;
      }

      // Returns the number of 10-peso coins in the Money object.
      public int getC10() {
          return c10;
      }

      // Returns the number of 20-peso coins in the Money object.
      public int getC20() {
          return c20;
      }

      // Returns the number of 50-peso bills in the Money object.
      public int getC50() {
          return c50;
      }

      // Returns the number of 100-peso bills in the Money object.
      public int getC100() {
          return c100;
      }

      // Returns the number of 200-peso bills in the Money object.
      public int getC200() {
          return c200;
      }

      // Returns the number of 500-peso bills in the Money object.
      public int getC500() {
          return c500;
      }

      // Returns the number of 1000-peso bills in the Money object.
      public int getC1000() {
          return c1000;
      }

      // Decreases the 1000-peso bill count by one.
      public void removeC1000() {
        c1000--;
      }

      // Decreases the 500-peso bill count by one.
      public void removeC500() {
        c500--;
      }

      // Decreases the 200-peso bill count by one.
      public void removeC200() {
        c200--;
      }

      // Decreases the 100-peso bill count by one.
      public void removeC100() {
        c100--;
      }

      // Decreases the 50-peso bill count by one.
      public void removeC50() {
        c50--;
      }

      // Decreases the 20-peso coin count by one.
      public void removeC20() {
        c20--;
      }

      // Decreases the 10-peso coin count by one.
      public void removeC10() {
        c10--;
      }

      // Decreases the 5-peso coin count by one.
      public void removeC5() {
        c5--;
      }

      // Decreases the 1-peso coin count by one.
      public void removeC1() {
        c1--;
      }

      // Returns the count of each denomination in the Money object as a string.
      public String listDenoms() {
        String denoms = "";

        if (c1000 > 0) {
            denoms += "P1000 - " + c1000 + " bill/s\n";
        }

        if (c500 > 0) {
            denoms += "P500 - " + c500 + " bill/s\n";
        }

        if (c200 > 0) {
            denoms += "P200 - " + c200 + " bill/s\n";
        }

        if (c100 > 0) {
            denoms += "P100 - " + c100 + " bill/s\n";
        }

        if (c50 > 0) {
            denoms += "P50 - " + c50 + " bill/s\n";
        }

        if (c20 > 0) {
            denoms += "P20 - " + c20 + " coin/s\n";
        }

        if (c10 > 0) {
            denoms += "P10 - " + c10 + " coin/s\n";
        }

        if (c5 > 0) {
            denoms += "P5 - " + c5 + " coin/s\n";
        }

        if (c1 > 0) {
            denoms += "P1 - " + c1 + " coin/s\n";
        }

        return denoms;
      }
}
