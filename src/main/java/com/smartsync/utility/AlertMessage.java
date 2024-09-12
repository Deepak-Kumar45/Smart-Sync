package com.smartsync.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertMessage {
    private String alertMessage;
    private AlertMessageType type = AlertMessageType.blue;
}
