terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "2.46.0"
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
    name       = "default"
    size       = var.node_size
    auto_scale = true
    min_nodes  = 1
    max_nodes  = 3
  }
}

output "kubeconfig" {
  value = digitalocean_kubernetes_cluster.nequi_cluster.kube_config[0].raw_config
  sensitive = true
}
