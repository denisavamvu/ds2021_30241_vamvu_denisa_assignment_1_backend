package ro.tuc.ds2020.dtos.builders;


import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.entities.Device;

public class DeviceBuilder {

    private DeviceBuilder() {

    }

    public static DeviceDetailsDTO toDeviceDetailsDTO(Device device) {
        return new DeviceDetailsDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMax_consumption(), device.getAverage_consumption(), device.getUser());
    }
    public static Device toEntity(DeviceDetailsDTO deviceDetailsDTO) {
        return new Device(deviceDetailsDTO.getId(), deviceDetailsDTO.getDescription(), deviceDetailsDTO.getMax_consumption(), deviceDetailsDTO.getAverage_consumption() );
    }
}
