'use strict';

angular.module('creditCardApp').controller('CreditCardController',
    ['CreditCardService', '$scope',  function( CreditCardService, $scope) {

        var self = this;
        self.creditCardInput = {};
        self.users=[];

        self.submit = submit;
        self.getAllCreditCard = getAllCreditCard;
        self.addCreditCard = addCreditCard;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Saving New credit card');
            addCreditCard(self.creditCardInput);
        }

        function addCreditCard(creditCardInput) {
            console.log('About to add new credit card');
            CreditCardService.addCreditCard(creditCardInput)
                .then(
                    function (response) {
                        console.log('Credit card added successfully');
                        self.successMessage = 'Credit card added successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.creditCardInput={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while adding new credit card');
                        self.errorMessage = 'Error while adding new credit card: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function getAllCreditCard(){
            return CreditCardService.getAllCreditCard();
        }
    }]);