package com.sin.imperial.court.entity;

import java.util.Objects;

public class Emp {

        private Integer empId;

        private String empName;

        private String empPosition;

        private String loginAccount;

        private String loginPassword;

        public Emp() {
        }

        @Override
        public String toString() {
                return "Emp{" +
                        "empId=" + empId +
                        ", empName='" + empName + '\'' +
                        ", empPosition='" + empPosition + '\'' +
                        ", loginAccount='" + loginAccount + '\'' +
                        ", loginPassword='" + loginPassword + '\'' +
                        '}';
        }

        public Integer getEmpId() {
                return empId;
        }

        public void setEmpId(Integer empId) {
                this.empId = empId;
        }

        public String getEmpName() {
                return empName;
        }

        public void setEmpName(String empName) {
                this.empName = empName;
        }

        public String getEmpPosition() {
                return empPosition;
        }

        public void setEmpPosition(String empPosition) {
                this.empPosition = empPosition;
        }

        public String getLoginAccount() {
                return loginAccount;
        }

        public void setLoginAccount(String loginAccount) {
                this.loginAccount = loginAccount;
        }

        public String getLoginPassword() {
                return loginPassword;
        }

        public void setLoginPassword(String loginPassword) {
                this.loginPassword = loginPassword;
        }
}
