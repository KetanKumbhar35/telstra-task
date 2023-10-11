Feature: SIM Card Activation

  Scenario: Successful SIM Card Activation
    Given a SIM card with ICCID "1255789453849037777" is ready for activation
    When the SIM card is activated
    Then the activation status should be "successful"

  Scenario: Failed SIM Card Activation
    Given a SIM card with ICCID "8944500102198304826" is ready for activation
    When the SIM card is activated
    Then the activation status should be "failed"