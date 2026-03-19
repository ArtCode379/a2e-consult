package service.a2econsult.app.data.repository

import service.a2econsult.app.R
import service.a2econsult.app.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalTime

class ServiceRepository {
    private val services: List<ServiceModel> = listOf(
        ServiceModel(
            id = 1,
            name = "Strategic Planning",
            description = "Develop comprehensive business strategies that align your organisation with market opportunities. Our consultants analyse competitive landscapes, identify growth vectors, and create actionable roadmaps that drive long-term value creation and sustainable competitive advantage.",
            price = 250.00,
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.hero1,
        ),
        ServiceModel(
            id = 2,
            name = "Organisational Development",
            description = "Transform your organisational structure and culture to maximise performance. We design frameworks that improve communication, foster innovation, and build resilient teams capable of adapting to evolving business demands.",
            price = 220.00,
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(13, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.hero2,
        ),
        ServiceModel(
            id = 3,
            name = "Human Resources Advisory",
            description = "Optimise your workforce strategy with expert HR consultancy. From talent acquisition and retention programmes to performance management systems, we help build a people-first organisation that attracts and nurtures top talent.",
            price = 200.00,
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(12, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.hero3,
        ),
        ServiceModel(
            id = 4,
            name = "Operations Management",
            description = "Streamline your operations for peak efficiency. We evaluate end-to-end processes, eliminate bottlenecks, implement lean methodologies, and establish key performance indicators that ensure continuous operational improvement.",
            price = 230.00,
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(16, 0)),
            imageRes = R.drawable.hero1,
        ),
        ServiceModel(
            id = 5,
            name = "Change Management",
            description = "Navigate complex organisational transitions with confidence. Our change management specialists guide your teams through mergers, restructuring, technology adoption, and cultural shifts while minimising disruption and resistance.",
            price = 240.00,
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(12, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.hero2,
        ),
        ServiceModel(
            id = 6,
            name = "Leadership Development",
            description = "Cultivate the next generation of leaders within your organisation. Through executive coaching, leadership assessments, and bespoke training programmes, we develop the leadership capabilities your business needs to thrive.",
            price = 280.00,
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(13, 0), LocalTime.of(16, 0)),
            imageRes = R.drawable.hero3,
        ),
        ServiceModel(
            id = 7,
            name = "Digital Transformation",
            description = "Embrace the digital future with a clear transformation strategy. We assess your digital maturity, identify automation opportunities, recommend technology solutions, and guide implementation to ensure your business stays competitive.",
            price = 300.00,
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.hero1,
        ),
        ServiceModel(
            id = 8,
            name = "Risk Management",
            description = "Protect your business with robust risk assessment and mitigation frameworks. We identify operational, financial, and strategic risks, then develop comprehensive contingency plans and governance structures to safeguard your assets.",
            price = 260.00,
            availableTime = listOf(LocalTime.of(10, 0), LocalTime.of(12, 0), LocalTime.of(15, 0)),
            imageRes = R.drawable.hero2,
        ),
        ServiceModel(
            id = 9,
            name = "Financial Advisory",
            description = "Make informed financial decisions with expert guidance. Our financial consultants provide budgeting frameworks, investment analysis, cost optimisation strategies, and financial modelling to strengthen your fiscal health.",
            price = 270.00,
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(13, 0), LocalTime.of(16, 0)),
            imageRes = R.drawable.hero3,
        ),
        ServiceModel(
            id = 10,
            name = "Market Research",
            description = "Gain actionable insights into your target markets. We conduct thorough market analysis, competitor benchmarking, customer segmentation studies, and trend forecasting to inform your strategic decisions with reliable data.",
            price = 210.00,
            availableTime = listOf(LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(14, 0)),
            imageRes = R.drawable.hero1,
        ),
    )

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}
