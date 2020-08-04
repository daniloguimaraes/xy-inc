package com.xyinc.poiservice.service.impl

import com.xyinc.poiservice.exception.InvalidPointOfInterestException
import com.xyinc.poiservice.model.PointOfInterest
import com.xyinc.poiservice.repository.PointOfInterestRepository
import com.xyinc.poiservice.service.PointOfInterestService
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldNotThrowTheException
import org.amshove.kluent.shouldThrowTheException
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

/**
 * Unit testing for PointOfInterestServiceImpl
 *
 * @author Danilo Guimaraes
 */
@SpringBootTest
class PointOfInterestServiceImplTest {

    private val validPoiName : String = "An Awesome Place";

    @Autowired
    lateinit var sut: PointOfInterestService;

    @MockBean
    var pointOfInterestRepository : PointOfInterestRepository? = null

    @Test
    fun `Given a valid PointOfInterest, when validate it, then must not throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 10, 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldNotThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest without ID, when validate it, then must not throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(name = validPoiName, xCoordinate = 10, yCoordinate =  10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldNotThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with XCoordinate equals 0, when validate it, then must not throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 0, 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldNotThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with negative XCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, -10, 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with negative YCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, 10, -10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest with both X and Y Coordinates as Negative, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(1L, validPoiName, -10, -10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given an unnamed PointOfInterest, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, xCoordinate = 10, yCoordinate = 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest without XCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, name = validPoiName, yCoordinate = 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given a PointOfInterest without YCoordinate, when validate it, then must throw Exception`() {
        // GIVEN
        val poi = createPointOfInterest(id = 1L, name = validPoiName, xCoordinate = 10);

        // WHEN
        val validateResult = { sut.validate(poi) };

        // THEN
        validateResult shouldThrowTheException InvalidPointOfInterestException::class
    }

    @Test
    fun `Given 7 points of interest on database, when findAll, then it must return 7 POIs`() {
        // GIVEN
        given(pointOfInterestRepository?.findAll()).willReturn(listOfPointOfInterest())

        val size = sut.findAll().size

        size shouldBe 7
    }

    @Test
    fun `Given 7 points of interest on database AND I'm at (20,10), when i look for POI's not farthest than 10, then it must return 4 POIs`() {
        // GIVEN
        given(pointOfInterestRepository?.findAll()).willReturn(listOfPointOfInterest())
        val myXCoordinate = 20
        val myYCoordinate = 10

        // WHEN
        val allNearby = sut.findAllNearby(myXCoordinate, myYCoordinate, 10);

        // THEN
        allNearby.size shouldBe 4

        allNearby.any { it.name.equals("Lanchonete") }.shouldBeTrue()
        allNearby.any { it.name.equals("Joalheria") }.shouldBeTrue()
        allNearby.any { it.name.equals("Pub") }.shouldBeTrue()
        allNearby.any { it.name.equals("Supermercado") }.shouldBeTrue()
    }

    private fun createPointOfInterest(
            id: Long? = null,
            name: String = "",
            xCoordinate: Int = -1,
            yCoordinate: Int = -1): PointOfInterest {
        return PointOfInterest(id, name, xCoordinate, yCoordinate)
    }

    private fun listOfPointOfInterest(): Iterable<PointOfInterest>? {
        return listOf(
                createPointOfInterest(name = "Lanchonete", xCoordinate= 27, yCoordinate= 12),
                createPointOfInterest(name = "Posto", xCoordinate= 31, yCoordinate= 18),
                createPointOfInterest(name = "Joalheria", xCoordinate= 15, yCoordinate= 12),
                createPointOfInterest(name = "Floricultura", xCoordinate= 19, yCoordinate= 21),
                createPointOfInterest(name = "Pub", xCoordinate= 12, yCoordinate= 8),
                createPointOfInterest(name = "Supermercado", xCoordinate= 23, yCoordinate= 6),
                createPointOfInterest(name = "Churrascaria", xCoordinate= 28, yCoordinate= 2))
    }

}

