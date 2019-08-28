package Company;

public class Security {

        private String code;

        private String name_full;
        private String cfi;

        private Currency currency;


    private String id;

        private String date_to;

        private State state;

        private String state_reg_date;

        public String getCode ()
        {
            return code;
        }

        public void setCode (String code)
        {
            this.code = code;
        }

        public String getname_full ()
        {
            return name_full;
        }

        public void setname_full (String name_full)
        {
            this.name_full = name_full;
        }

        public String getCfi ()
        {
            return cfi;
        }

        public void setCfi (String cfi)
        {
            this.cfi = cfi;
        }

        public Currency getCurrency ()
        {
            return currency;
        }

        public void setCurrency (Currency currency)
        {
            this.currency = currency;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getDate_to ()
        {
            return date_to;
        }

        public void setDate_to (String date_to)
        {
            this.date_to = date_to;
        }

        public State getState ()
        {
            return state;
        }

        public void setState (State state)
        {
            this.state = state;
        }

        public String getState_reg_date ()
        {
            return state_reg_date;
        }

        public void setState_reg_date (String state_reg_date)
        {
            this.state_reg_date = state_reg_date;
        }

        @Override
        public String toString()
        {
            return "Class Security: Код = "+code+", полное имя владельца = "+name_full +", дата истечения = "+date_to;
        }
    }

