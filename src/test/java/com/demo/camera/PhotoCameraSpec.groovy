package com.demo.camera


import spock.lang.Specification

class PhotoCameraSpec extends Specification {


    def "Should power up the sensor when camera is switched on"() {
        given:
        ImageSensor powerSensor = Mock(ImageSensor)
        PhotoCamera camera = new PhotoCamera(powerSensor)

        when:
        camera.turnOn()

        then:
        1 * powerSensor.turnOn()
    }


    def "Should power down the sensor when camera is switched off"() {
        given:
        ImageSensor powerSensor = Mock(ImageSensor)
        PhotoCamera camera = new PhotoCamera(powerSensor)

        when:
        camera.turnOff()

        then:
        1 * powerSensor.turnOff()
    }

    def "When  camera is off, press blink do nothing"() {
        given:
        ImageSensor sensor = Mock(ImageSensor)
        PhotoCamera camera = new PhotoCamera(sensor)

        when:
        camera.pressButton()

        then:
        0 * sensor.read()
    }
    def "When camera is on, press blink write a photo"() {
        given:
        ImageSensor sensor = Mock(ImageSensor)
        Card card = Mock(Card)
        PhotoCamera camera = new PhotoCamera(sensor,card)

        when:
        camera.pressButton()

        then:
        1 * sensor.read()
        1 * card.write()
    }
}
