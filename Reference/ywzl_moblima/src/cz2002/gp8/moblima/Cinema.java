package cz2002.gp8.moblima;

/**
 * Representative of am instance of Cinema in MOBLIMA.
 */
public class Cinema {

    /**
     * A collection of Suites type for Cinemas.
     */
    public enum Suite {

        /**
         * Standard Suite
         */
        STANDARD {
		            @Override
		            public String toString() {
		                return "Standard";
		            }
		        },    	

        /**
         * Gold Class Suite
         */
        GOLD_CLASS {
                    @Override
                    public String toString() {
                        return "Gold Class";
                    }
                },

        /**
         * D Box
         */
        D_BOX {
                    @Override
                    public String toString() {
                        return "D-BOX";
                    }
                },

        /**
         * Gemini
         */
        GEMINI {
                    @Override
                    public String toString() {
                        return "Gemini: Two to View";
                    }
                },

        /**
         * Grand Seats
         */
        GRAND_SEATS {
                    @Override
                    public String toString() {
                        return "Grand Seats";
                    }
                },

        /**
         *
         */
        GVMAX {
                    @Override
                    public String toString() {
                        return "GVmax";
                    }
                },

        /**
         * GVMas Dolby
         */
        GVMAX_DOLBY {
                    @Override
                    public String toString() {
                        return "GVmax Dolby ATMOS";
                    }
                }
    }
    
	private String name;
	private Suite suite;
	private int col, row;
	
    /**
     *
     * @param name Name of the Cinema hall
     * @param row Number of Rows
     * @param col Number of Columns
     * @param suite Cinema.Suite type
     */
    public Cinema(String name, int row, int col, Suite suite) {
		this.name = name;
		this.row = row;
		this.col = col;
		this.suite = suite;
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the suite
     */
    public Suite getSuite() {
        return suite;
    }

    /**
     * @return the col
     */
    public int getCols() {
        return col;
    }

    /**
     * @return the row
     */
    public int getRows() {
        return row;
    }

}
