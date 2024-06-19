package projekt.karol.lesniewski.mapa;

public class DeviceRegister {

        private String device;

        private String phoneNumber;

    public DeviceRegister(String device, String phoneNumber) {
        this.device = device;
        this.phoneNumber = phoneNumber;
    }

    public DeviceRegister() {
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
