package com.ohashi.pixexample.factories.forms;

import com.ohashi.pixexample.entities.forms.UpdateAccountForm;

public class UpdateAccountFormFactory {
    public static UpdateAccountForm sample() {
        return new UpdateAccountForm(
                "Dummyzinho",
                0.0
        );
    }
}
