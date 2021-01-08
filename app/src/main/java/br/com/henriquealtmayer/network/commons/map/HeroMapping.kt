package br.com.henriquealtmayer.network.commons.map

import br.com.henriquealtmayer.network.commons.handleOptional
import br.com.henriquealtmayer.network.commons.model.data.HeroResponse
import br.com.henriquealtmayer.network.commons.model.domain.Hero

fun HeroResponse.toHero() = Hero(
    id.handleOptional(),
    name.handleOptional(),
    description.handleOptional(),
    thumbnail?.getUrl().handleOptional()
)

fun List<HeroResponse>.toHeroList() = map { it.toHero() }