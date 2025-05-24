terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "2.54.0"
    }
  }
}
provider "digitalocean" {
  token = var.do_token
}

resource "digitalocean_kubernetes_cluster" "nequi_cluster" {
  name    = var.cluster_name
  region  = var.region
  version = var.k8s_version

  node_pool {
    name       = "autoscale-pool-01"
    size       = var.node_size
    auto_scale = true
    min_nodes  = 1
    max_nodes  = 3
    tags = ["backend"]

    labels = {
      service  = "backend"
      priority = "high"
    }
  }
}

resource "digitalocean_kubernetes_node_pool" "autoscale-pool-01" {
  cluster_id = digitalocean_kubernetes_cluster.nequi_cluster.id
  name       = "autoscale-pool-01"
  size       = var.node_size
  auto_scale = true
  min_nodes  = 1
  max_nodes  = 3
  tags       = ["backend"]

  labels = {
    service  = "backend"
    priority = "high"
  }
}

output "kubeconfig" {
  value = digitalocean_kubernetes_cluster.nequi_cluster.kube_config[0].raw_config
  sensitive = true
}
